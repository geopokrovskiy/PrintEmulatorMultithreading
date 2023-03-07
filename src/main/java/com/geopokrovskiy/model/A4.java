package com.geopokrovskiy.model;

import com.geopokrovskiy.factory.DocumentFactory;

public class A4 extends AbstractDocument {

    public A4() {
        this.type = "A4";
        this.size = new Size(297, 210);
        this.printDuration = 2;
    }


}
