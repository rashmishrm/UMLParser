package com.sjsu.project.tryout;

public class Main {
    private String a;

    public String getA() {
	return a;
    }

    public void setA(String a) {
	this.a = a;
    }

    public void setSample(Main a) {

	a = new Main();
	System.out.println(a);
    }

    public static void main(String[] args) {
	final Main m = new Main();
	final Main s = (Main)null;
	m.setSample(s);

	System.out.println(s);
    }
}

