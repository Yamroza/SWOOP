module ItemExchangePortal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires slf4j.api;

    opens GUI;
    exports GUI;
    exports Classes;
    exports Database;
}


