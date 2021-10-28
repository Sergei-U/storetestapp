package ru.usov.storetestapp.Service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._core.BulkResponse;
import co.elastic.clients.elasticsearch._core.SearchRequest;
import co.elastic.clients.elasticsearch._core.SearchResponse;
import co.elastic.clients.elasticsearch._core.search.Hit;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.usov.storetestapp.Entity.Products;
import ru.usov.storetestapp.Repository.ProductRepository;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final String index;
    private final ElasticsearchClient client;

    public void addProduct(Products product) {
        this.productRepository.save(product);
    }

    public void editProduct(Products product) {
        this.productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        this.productRepository.deleteById(id);
    }

    public List<Products> allProduct() {
        return productRepository.findAll();
    }

    public Products getProduct(Long id) {
        return productRepository.getById(id);
    }

    public List<Products> findProductsByNameAndDescription(String request) {
        return this.productRepository.findByNameOrDescription(request, request);
    }

    public Products search(String input) throws IOException {
        return createProducts(createSearchRequest(input, 0, 10), input);
    }

    public Products next(Products products) throws IOException {
        int from = (int) (products.getId() + products.getPrice());
        final SearchRequest request = createSearchRequest(products.getDescription(), from, from);
        return createProducts(request, products.getName());
    }

    private Products createProducts(SearchRequest searchRequest, String input) throws IOException {
        final SearchResponse<Products> response = client.search(searchRequest, Products.class);
        if (response.hits().total().value() == 0) {
            return Products.EMPTY;
        }
        if (response.hits().hits().isEmpty()) {
            return Products.EMPTY;
        }


        response.hits().hits().forEach(hit -> hit.source().setId(Long.valueOf(hit.id())));
        final List<Object> products = response.hits().hits().stream().map(Hit::source).collect(Collectors.toList());
        return (Products) products;
    }

    private SearchRequest createSearchRequest(String input, int from, int size) {
        return new SearchRequest(builder -> builder
                .from(from)
                .size(size)
                .query(q -> q.multiMatch(mmb -> mmb.query(input).fields("name", "description"))));
    }

    public void save(Products product) throws IOException {
        save(Collections.singletonList(product));
    }

    public void save(List<Products> products) throws IOException {
        final BulkResponse response = client.bulk(builder -> {
            for (Products product : products) {
                builder.addOperation(b -> b.index(ib -> {
                    if (product.getId() != null) {
                        ib.id(String.valueOf(product.getId()));
                    }
                    return ib.index(index);
                }));
                builder.addDocument(product);
            }
            return builder;
        });
    }
}
