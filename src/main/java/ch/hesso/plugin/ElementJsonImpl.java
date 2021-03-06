package ch.hesso.plugin;

import ch.hesso.xmleditor.editdom.Element;
import com.google.gson.JsonElement;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ElementJsonImpl implements Element {
    protected JsonElement element;
    private final String name;

    public ElementJsonImpl(JsonElement element) {
        this.element = element;
        this.name = "root";
    }

    public ElementJsonImpl(JsonElement element, String name) {
        this.element = element;
        this.name = name;
    }

    @Override
    public List<Element> getChildren() {
        if (!this.element.isJsonObject()) {
            return Collections.emptyList();
        }
        return this.element.getAsJsonObject().keySet().stream()
                           .map(key -> new ElementJsonImpl(this.element.getAsJsonObject().get(key), key))
                           .collect(Collectors.toList());
    }

    @Override
    public String getText() {
        return element.getAsString();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "ElementJsonImpl{" +
                "element=" + element +
                ", name='" + name + '\'' +
                '}';
    }

    public void addProperty(String name, String text){
        element.getAsJsonObject().addProperty(name, text);
    }
}

