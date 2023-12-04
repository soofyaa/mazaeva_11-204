module ru.itis.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.net.http;
    requires com.fasterxml.jackson.databind;

    opens ru.itis.javafx to javafx.fxml;
    exports ru.itis.javafx;
    exports ru.itis.utils;
}
