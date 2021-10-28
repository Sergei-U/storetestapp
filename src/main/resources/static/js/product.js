function getIndex(list, id) {
    for (var i = 0; i < list.length; i++) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var productApi = Vue.resource('/product{/id}');

Vue.component('product-form', {
    props: ['products', 'productAttr'],
    data: function () {
        return {
            category: '',
            id: '',
            name: '',
            description: '',
            img: '',
            price: '',
            quantity: ''
        }
    },
    watch: {
        productAttr: function (newVal, oldVal) {
            this.id = newVal.id;
            this.name = newVal.name;
            this.description = newVal.description;
            this.img = newVal.img;
            this.price = newVal.price;
            this.quantity = newVal.quantity;

        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write name" v-model="name" />' +
        '<input type="text" placeholder="Write description" v-model="description" />' +
        '<input type="text" placeholder="Write img" v-model="img" />' +
        '<input type="text" placeholder="Write price" v-model="price" />' +
        '<input type="number" placeholder="Write quantity" v-model="quantity" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function () {
            var product = {text: this.text};

            if (this.id) {
                productApi.update({id: this.id}, product).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.products, data.id);
                        this.products.splice(index, 1, data);
                        this.name = ''
                        this.description = ''
                        this.img = ''
                        this.price = ''
                        this.quantity = ''
                        this.id = ''
                    })
                )
            } else {
                productApi.save({}, product).then(result =>
                    result.json().then(data => {
                        this.products.push(data);
                        this.name = ''
                        this.name = ''
                        this.description = ''
                        this.img = ''
                        this.price = ''
                        this.quantity = ''
                    })
                )
            }
        }
    }
});

Vue.component('product-row', {
    props: ['product', 'editMethod', 'products'],
    template: '<div>' +
        '<i>({{ product.id }})</i> {{ product.text }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function () {
            this.editMethod(this.product);
        },
        del: function () {
            productApi.remove({id: this.product.id}).then(result => {
                if (result.ok) {
                    this.products.splice(this.products.indexOf(this.product), 1)
                }
            })
        }
    }
});

Vue.component('products-list', {
    props: ['products'],
    data: function () {
        return {
            product: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<product-form :products="products" :productAttr="product" />' +
        '<product-row v-for="product in products" :key="product.id" :product="product" ' +
        ':editMethod="editMethod" :products="products" />' +
        '</div>',
    methods: {
        editMethod: function (product) {
            this.product = product;
        }
    }
});

var app = new Vue({
    el: '#app',
    template:
        '<div>' +
        '<div v-if="!profile">Необходимо авторизоваться через <a href="/login">Google</a></div>' +
        '<div v-else>' +
        '<div>{{profile.name}}&nbsp;<a href="/logout">Выйти</a></div>' +
        '<products-list :products="products" />' +
        '</div>' +
        '</div>',
    data: {
        products: frontendData.products,
        profile: frontendData.profile
    },
    created: function () {
//    productApi.get().then(result =>
//        result.json().then(data =>
//            data.forEach(product => this.products.push(product))
//        )
//    )
    },
});