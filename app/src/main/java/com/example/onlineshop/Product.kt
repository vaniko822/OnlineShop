package com.example.onlineshop

data class Product(
    var carid : Int ?= null,
    var name : String ?= null,
    var pictureurl : String ?= null,
    var price : Int ?= null,
    var year : Int ?= null
)
