package com.company.jmixpmflowbase.view.nativejson;


import com.company.jmixpmflowbase.view.main.MainView;

import com.vaadin.flow.router.Route;
import io.jmix.flowui.view.StandardView;
import io.jmix.flowui.view.ViewController;
import io.jmix.flowui.view.ViewDescriptor;

@Route(value = "native-json-view", layout = MainView.class)
@ViewController("NativeJsonView")
@ViewDescriptor("native-json-view.xml")
public class NativeJsonView extends StandardView {
}