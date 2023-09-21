import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    /*
        В данном коде реализован простой сервер,
        который прослушивает определенный порт (в данном случае, порт 8080).
        Когда клиентское соединение устанавливается,
        сервер получает запрос от клиента и отправляет ему ответ, содержащий HTML-страницу.
     */

    public static void main(String[] args) throws Exception {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);

        while (true) {
            Socket clientSocket = serverSocket.accept();

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                if (s.isEmpty()) {
                    break;
                }
            }

            out.write("HTTP/1.1 200 OK\n" +
                    "Date: Wed, 11 Feb 2009 11:20:59 GMT\n" +
                    "Server: Apache\n" +
                    "X-Powered-By: PHP/5.2.4-2ubuntu5wm1\n" +
                    "Last-Modified: Wed, 11 Feb 2009 11:20:59 GMT\n" +
                    "Content-Language: ru\n" +
                    "Content-Type: text/html; charset=utf-8\n" +
                    "Content-Length: 1234\n" +
                    "Connection: close" + "\n\n" +
                    "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "<head>\n" +
                    "    <meta charset=\"UTF-8\">\n" +
                    "    <title>Profile</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"./style.css\">\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "<h1 style=\"background-color: blue\">My profile</h1>\n" +
                    "<img src=\"https://cs12.pikabu.ru/post_img/big/2022/08/09/7/1660042588198544626.png\" style=\"width: 10%\">\n" +
                    "<div style=\"background-color: palevioletred\" class=\"some-class\" id=\"someid\">\n" +
                    "<p>My name is Sofya. I'm studying at <span style=\"background-color: blue\">ITIS</span>. My contacts:</p>\n" +
                    "<ul>\n" +
                    "    <li><a href=\"https://vk.com/son404\">VK</a></li>\n" +
                    "    <li><a href=\"https://t.me/hogpog\">TG</a></li>\n" +
                    "</ul>\n" +
                    "<p>Something else...</p>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>");
            out.close();
            in.close();
            clientSocket.close();
        }
    }
}