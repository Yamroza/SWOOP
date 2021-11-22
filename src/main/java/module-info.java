module ItemExchangePortal {
   requires javafx.controls;
   requires javafx.fxml;

   opens GUI;
   exports GUI;
   exports Classes;
}


