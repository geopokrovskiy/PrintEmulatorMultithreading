package com.geopokrovskiy.factory;

import com.geopokrovskiy.model.A3;
import com.geopokrovskiy.model.AbstractDocument;

public class A3Factory implements DocumentFactory{
    @Override
    public AbstractDocument newInstance() {
        return new A3();
    }
}
