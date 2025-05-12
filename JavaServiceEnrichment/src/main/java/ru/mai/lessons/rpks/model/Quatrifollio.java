package ru.mai.lessons.rpks.model;

public record Quatrifollio<A, B, C, D>(A ruleId, B fieldNameInMongo, C fieldValueInMongo, D valueDefault) {}
