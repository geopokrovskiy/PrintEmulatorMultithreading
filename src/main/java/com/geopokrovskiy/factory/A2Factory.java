package com.geopokrovskiy.factory;

import com.geopokrovskiy.model.A2;
import com.geopokrovskiy.model.AbstractDocument;

public class A2Factory implements DocumentFactory{
    @Override
    public AbstractDocument newInstance() {
        return new A2();
    }
}
