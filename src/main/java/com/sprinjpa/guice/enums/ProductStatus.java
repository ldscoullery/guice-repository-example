package com.sprinjpa.guice.enums;

public enum ProductStatus {

    NEW,
    USED;

    public static ProductStatus getStatus(String value) {
        try {
            return ProductStatus.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException ignored) {
            System.out.println("Exception in status conversion");
        }
        return null;
    }
}
