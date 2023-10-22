//package com.mattdag.f1.udp.kafka.producer.serdes
//
//import com.fasterxml.jackson.annotation.JsonSubTypes
//
//import com.fasterxml.jackson.annotation.JsonTypeInfo
//
//
//// being explicit for the example
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "_t")
//@JsonSubTypes(
//    JsonSubTypes.Type(value = CarStatusSerde::class, name = "CarStatus"),
//)
//interface JsonSerdeCompatible
