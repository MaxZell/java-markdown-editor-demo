package com.example.mddemo;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebView;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

public class HelloController {
    @FXML
    public TextArea editArea;
    @FXML
    public WebView previewArea;

    @FXML
    public void initialize() {
        editArea.textProperty().addListener((observable, oldValue, newValue) -> updatePreview(editArea.getText()));
    }
    private final Parser parser = Parser.builder().build();
    private final HtmlRenderer renderer = HtmlRenderer.builder().build();

    private String markdownText = "";

    public void updatePreview(String text) {
        markdownText = parseMarkdown(text);
        previewArea.getEngine().loadContent(markdownText);
    }

    String parseMarkdown(String newText) {
        Node astRoot = parser.parse(newText);
        return renderer.render(astRoot);
    }

    @FXML
    private void insertBold(){
        String selectedText = editArea.getSelectedText();
        editArea.getCursor();
        if (!selectedText.isEmpty()) {
            String boldText = "**" + selectedText + "**";
            int startIndex = editArea.getSelection().getStart();
            int endIndex = editArea.getSelection().getEnd();
            editArea.replaceText(startIndex, endIndex, boldText);
        }
    }

    @FXML
    private void insertItalic(){
        String selectedText = editArea.getSelectedText();
        if (!selectedText.isEmpty()) {
            int startIndex = editArea.getSelection().getStart();
            int endIndex = editArea.getSelection().getEnd();
            String boldText = "*" + selectedText + "*";
            editArea.replaceText(startIndex, endIndex, boldText);
        }
    }

}