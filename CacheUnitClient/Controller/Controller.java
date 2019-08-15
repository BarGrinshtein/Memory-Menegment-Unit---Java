package com.hit.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public interface Controller extends Observer
{
	public void update(Observable o, Object arg);
}
