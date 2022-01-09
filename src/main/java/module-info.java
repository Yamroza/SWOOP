module ItemExchangePortal {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.slf4j;
    requires org.controlsfx.controls;
    requires commons.codec;

    opens GUI;
    exports GUI;
    exports Classes;
    exports Database;
}


