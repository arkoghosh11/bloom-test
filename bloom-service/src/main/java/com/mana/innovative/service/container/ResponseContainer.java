package com.mana.innovative.service.container;

import com.mana.innovative.exception.response.ErrorContainer;

import javax.xml.bind.annotation.XmlElement;

/**
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 */
public class ResponseContainer<T> {

    private int count;

    private T payload;
    private ErrorContainer errorContainer;

    @XmlElement(name = "count", namespace = "http://localhost:8080/bloom-test/rest/")
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlElement(name = "payload",  namespace = "http://localhost:8080/bloom-test/rest/")
    public T getPayload() {
        return payload;
    }

    public void setPayload(T payload) {
        this.payload = payload;
    }

    @XmlElement(name = "error_container", namespace = "http://localhost:8080/bloom-test/rest/")
    public ErrorContainer getErrorContainer() {
        return errorContainer;
    }

    public void setErrorContainer(ErrorContainer errorContainer) {
        this.errorContainer = errorContainer;
    }
}
