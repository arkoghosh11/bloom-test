package com.mana.innovative.service.container;

import com.mana.innovative.dto.payload.ItemsPayload;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * Created by alex1 on 1/23/2015.
 * This is a domain class
 */
@XmlRootElement(name = "response", namespace = "http://localhost:8080/bloom-test/rest/")
@XmlSeeAlso(value = {ItemsPayload.class, ResponseContainer.class})
public class ItemResponseContainer<T> extends ResponseContainer<T> {
}