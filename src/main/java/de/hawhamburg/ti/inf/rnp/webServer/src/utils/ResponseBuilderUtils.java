package de.hawhamburg.ti.inf.rnp.webServer.src.utils;

public final class ResponseBuilderUtils {
    public static final String SERVER_HEADER = "Server: RNP WebServer";
    public static final String CONTENT_TYPE = "Content-Type: ";
    public static final String RESPONSE_OKAY = "HTTP/1.1 200 OK";
    public static final String RESPONSE_BAD_REQUEST = "HTTP/1.1 400 BAD REQUEST";
    public static final String RESPONSE_NOT_FOUND = "HTTP/1.1 404 NOT FOUND";
    public static final String RESPONSE_REQUEST_HEADER_FIELDS_TOO_LARGE = "HTTP/1.1 431 REQUEST HEADER FIELDS TOO LARGE";

    public static final String CONTENT_HEADER = "<h1>Welcome to the Computer Networking WebServer</h2>";
    public static final String CONTENT_PARAGRAPH =
            "<p>Lorem ipsum dolor sit amet, consetetur sadipscing elitr, " +
                    "sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. " +
                    "At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                    "no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, " +
                    "consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, " +
                    "sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, " +
                    "no sea takimata sanctus est Lorem ipsum dolor sit amet.</p>";

    public static final String INDEX_HTML =
            "<!doctype html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>Example Domain</title>\n" +
                    "\n" +
                    "    <meta charset=\"utf-8\" />\n" +
                    "    <meta http-equiv=\"Content-type\" content=\"text/html; charset=utf-8\" />\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" />\n" +
                    "    <style type=\"text/css\">\n" +
                    "    body {\n" +
                    "        background-color: #f0f0f2;\n" +
                    "        margin: 0;\n" +
                    "        padding: 0;\n" +
                    "        font-family: -apple-system, system-ui, BlinkMacSystemFont, \"Segoe UI\", \"Open Sans\", \"Helvetica Neue\", Helvetica, Arial, sans-serif;\n" +
                    "        \n" +
                    "    }\n" +
                    "    div {\n" +
                    "        width: 600px;\n" +
                    "        margin: 5em auto;\n" +
                    "        padding: 2em;\n" +
                    "        background-color: #fdfdff;\n" +
                    "        border-radius: 0.5em;\n" +
                    "        box-shadow: 2px 3px 7px 2px rgba(0,0,0,0.02);\n" +
                    "    }\n" +
                    "    a:link, a:visited {\n" +
                    "        color: #38488f;\n" +
                    "        text-decoration: none;\n" +
                    "    }\n" +
                    "    @media (max-width: 700px) {\n" +
                    "        div {\n" +
                    "            margin: 0 auto;\n" +
                    "            width: auto;\n" +
                    "        }\n" +
                    "    }\n" +
                    "    </style>    \n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "<div>\n" +
                    "    <h1>Example Domain</h1>\n" +
                    "    <p>This domain is for use in illustrative examples in documents. You may use this\n" +
                    "    domain in literature without prior coordination or asking for permission.</p>\n" +
                    "    <p><a href=\"https://www.iana.org/domains/example\">More information...</a></p>\n" +
                    "</div>\n" +
                    "</body>\n" +
                    "</html>\n";
}
