
function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return i;
        }
    }

    return -1;
}

var categoryApi = Vue.resource('/category{/id}');

Vue.component('category-form', {
    props: ['categorys', 'categoryAttr'],
    data: function() {
        return {
            name: '',
            id: '',
            description: ''
        }
    },
    watch: {
        categoryAttr: function(newVal, oldVal) {
            this.name = newVal.name;
            this.id = newVal.id;
            this.description = newVal.description;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Write name" v-model="name" />' +
        '<input type="text" placeholder="Write descriprion" v-model="description" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var category = { name: this.name, description: this.description };

            if (this.id) {
                categoryApi.update({name: this.name}, {description: this.description}, category).then(result =>
                    result.json().then(data => {
                        var index = getIndex(this.categorys, data.id);
                        this.categorys.splice(index, 1, data);
                        this.name = ''
                        this.id = ''
                        this.description = ''
                    })
                )
            } else {
                categoryApi.save({}, category).then(result =>
                    result.json().then(data => {
                        this.categorys.push(data);
                        this.name = ''
                        this.description = ''
                    })
                )
            }
        }
    }
});

Vue.component('category-row', {
    props: ['category', 'editMethod', 'categorys'],
    template: '<div>' +
        '<i>({{ category.id }})</i> {{ category.text }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.category);
        },
        del: function() {
            categoryApi.remove({id: this.category.id}).then(result => {
                if (result.ok) {
                    this.categorys.splice(this.categorys.indexOf(this.category), 1)
                }
            })
        }
    }
});

Vue.component('categorys-list', {
    props: ['categorys'],
    data: function() {
        return {
            category: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<category-form :categorys="categorys" :categoryAttr="category" />' +
        '<category-row v-for="category in categorys" :key="category.id" :category="category" ' +
        ':editMethod="editMethod" :categorys="categorys" />' +
        '</div>',
    methods: {
        editMethod: function(category) {
            this.category = category;
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
        '<categorys-list :categorys="categorys" />' +
        '</div>' +
        '</div>',
    data: {
        categorys: frontendData.categorys,
        profile: frontendData.profile
    },
    created: function() {
//    categoryApi.get().then(result =>
//        result.json().then(data =>
//            data.forEach(category => this.categorys.push(category))
//        )
//    )
    },
});