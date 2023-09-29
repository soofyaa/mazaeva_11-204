import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

public class MyServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/plain");
        String json = req.getReader().lines().reduce("", (accumulator, actual) -> accumulator + actual);
        int sum = Arrays.stream(json.replace("[", "").replace("]", "").split(", "))
                .mapToInt(Integer::parseInt)
                .filter(n -> n > 5)
                .sum();
        resp.getWriter().println(sum);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>\n" +
                "<html lang=\"ru\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Новый Год</title>\n" +
                "</head>\n" +
                "<body bgcolor=\"#f4b598\">\n" +
                "    <div style=\"color: #6d2920\">\n" +
                "        <h1 style=\"background-color: #fbceb1; text-align: center;\">Новый Год</h1>\n" +
                "        <img src=\"https://i.pinimg.com/564x/25/d3/ed/25d3ed63e07cde0314c6a088b8bcc1ca.jpg\"\n" +
                "             alt=\"cat-tree\"\n" +
                "             width=\"24%\"\n" +
                "             border=\"12px\"\n" +
                "             style=\"float: left;\n" +
                "             margin-right: 5%;\n" +
                "             margin-left: 8%;\n" +
                "             margin-bottom: 2%; border-color: #fbceb1\t\" >\n" +
                "\n" +
                "        <div style=\"margin-right: 8%; font-size: 20px\">\n" +
                "\n" +
                "            <p>\n" +
                "                Зима засыпала искрящимся снегом городские улицы, надела на деревья роскошный наряд.\n" +
                "                Мороз покрыл причудливыми узорами окна домов, заковал реку в ледяные оковы.\n" +
                "                На крышах домов выросли сосульки. <span style=\"background-color: #fbceb1\">Это значит, что скоро Новый Год! </span><br>\n" +
                "            </p>\n" +
                "            <ul style=\"margin-top: 2%\">\n" +
                "                <b>Дата наступления Нового года у различных народов:</b>\n" +
                "                <li>1 января — Новый год по григорианскому, юлианскому и новоюлианскому календарям.</li>\n" +
                "                <li>Между 21 января и 21 февраля (один из дней) — китайский Новый год.</li>\n" +
                "                <li>С 13 апреля по 15 апреля — Сонгкран — тайский Новый год.</li>\n" +
                "                <li>14 апреля — бенгальский Новый год (Бангладеш).</li>\n" +
                "            </ul>\n" +
                "\n" +
                "            <p style=\"margin-top: 2%\">\n" +
                "                В России Новый Год – это самый волшебный, самый красивый, самый таинственный и веселый праздник!\n" +
                "                С начала декабря город начинает готовиться к торжеству: улицы украшены разноцветными огнями,\n" +
                "                на площадях города выросли величавые ёлки, в витринах магазинов мерцают новогодние гирлянды.\n" +
                "                Создается ощущение, что ты попал в сказку.\n" +
                "                В это время даже воздух пропитан новогодним волшебством.\n" +
                "            </p>\n" +
                "\n" +
                "            <div style=\"margin-left: 8%\">\n" +
                "                <p style=\"margin-top: 4%\">\n" +
                "                    Каждый человек ждет новогоднего волшебства.\n" +
                "                    Многие верят, что новогодняя ночь исполняет все желания.\n" +
                "                    Особенно ждут этот праздник дети, ведь именно им\n" +
                "                    <a href=\"https://ru.wikipedia.org/wiki/%D0%94%D0%B5%D0%B4_%D0%9C%D0%BE%D1%80%D0%BE%D0%B7\"\n" +
                "                       target=\"_blank\" style=\"color: #6d2920; background-color: #fbceb1\">\n" +
                "                       Дедушка Мороз\n" +
                "                    </a>\n" +
                "                    приносит много интересных подарков.\n" +
                "                    Люди посылают новогодние открытки своим родственникам и друзьям, а также делают друг другу сюрпризы.\n" +
                "                </p>\n" +
                "\n" +
                "                <p>\n" +
                "                    <b>ABBA - Happy New Year</b>\n" +
                "                </p>\n" +
                "\n" +
                "                <audio controls style=\"width: 100%;\n" +
                "                  height: 7vh;\n" +
                "                  background-color: #fbceb1\">\n" +
                "                    <source src=\"https://notka.net/wp-content/uploads/ABBA-Happy-New-Year.mp3\" type=\"audio/mpeg\">\n" +
                "                    Ваш браузер не поддерживает аудио элемент.\n" +
                "                </audio>\n" +
                "\n" +
                "                <p>\n" +
                "                    А теперь немного о том, где можно провести новогоднюю ночь.\n" +
                "                </p>\n" +
                "\n" +
                "                <table>\n" +
                "                    <tr>\n" +
                "                        <th>Место</th>\n" +
                "                        <th>Описание</th>\n" +
                "                        <th>Цена</th>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>Прага, Чехия</td>\n" +
                "                        <td>Рождественский ярмарка на площади Староместской, замок Праги, потрясающая архитектура и атмосфера</td>\n" +
                "                        <td>150-500 евро</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>Лондон, Великобритания</td>\n" +
                "                        <td>Фейерверки над Лондонским собором, катание на горках в Хайд-парке, иллюминация вида на Темзу</td>\n" +
                "                        <td>200-1000 фунтов стерлингов</td>\n" +
                "                    </tr>\n" +
                "                    <tr>\n" +
                "                        <td>Москва, Россия</td>\n" +
                "                        <td>Красочное представление на Красной площади, иллюминация, ледяные скульптуры, гуляния по Арбату</td>\n" +
                "                        <td>5000-10000 рублей</td>\n" +
                "                    </tr>\n" +
                "                </table>\n" +
                "\n" +
                "                <style>\n" +
                "                    table {\n" +
                "                        width: 100%;\n" +
                "                        border-collapse: collapse;\n" +
                "                    }\n" +
                "\n" +
                "                    th, td {\n" +
                "                        padding: 8px;\n" +
                "                        text-align: left;\n" +
                "                        border-bottom: 1px solid #6d2920;\n" +
                "                    }\n" +
                "\n" +
                "                    th {\n" +
                "                        background-color: #fbceb1;\n" +
                "                    }\n" +
                "                </style>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>");
    }
}
