package service.api;

import Enums.Users;
import Interfaces.RestClient;
import org.json.JSONObject;
import org.openqa.selenium.Cookie;
import service.Config;
import service.UserFactory;
import service.ui.Driver;
import service.ui.DriverManager;

import java.util.HashMap;
import java.util.List;

public class ApiClient {

    private RestClient client = null;
    private String username;
    private String password;
    private String authToken = null;
    public User user = new User();
    public Widget widget = new Widget();
    public Dashboard dashboard = new Dashboard();

    public ApiClient(Users role) {
        service.User user = UserFactory.getUser(role.toString());
        this.username = user.getName();
        this.password = user.getPassword();
        init();
    }

    public ApiClient(String username, String password) {
        this.username = username;
        this.password = password;
        init();
    }

    // Public getter
    public String getToken() {
        return authToken;
    }

    public class User {
        private static final String GET_ALL_USERS = "/user/all";
        private static final String CREATE_USER = "/user";
        private static final String USER = "/user/<id>";

        public String update(String userId, HashMap<String, String> values) {
            JSONObject obj = new JSONObject();
            for (String key : values.keySet()) {
                obj.put(key, values.get(key));
            }
            return client.putWithToken(USER.replace("<id>", userId), obj.toString(), authToken);
        }

        public String logout(){
            return client.deleteWithoutBaseUrl(Config.getUrl() + "uat/sso/me", authToken);
        }

        public String delete(String userId) {
            return client.deleteWithToken(USER.replace("<id>", userId), authToken);
        }

        public String getAll() {
            return client.getWithToken(GET_ALL_USERS, authToken);
        }

        public String getById(String userId) {
            return client.getWithToken(USER.replace("<id>", userId), authToken);
        }

        public String create(String username, String password, String full_name, String accountRole, String projectRole) {
            JSONObject obj = new JSONObject();
            obj.put("login", username)
                    .put("password", password)
                    .put("full_name", full_name)
                    .put("email", username + "@test.com")
                    .put("accountRole", accountRole)
                    .put("projectRole", projectRole)
                    .put("default_project", "default_personal");
            return client.postWithToken(CREATE_USER, obj.toString(), authToken);
        }

    }

    public class Widget {

        private static final String ALL_WIDGETS = "/<project>/widget/names/all";
        private static final String WIDGET = "/<project>/widget";
        private static final String ADD_WIDGET_TO_DASHBOARD = "/<project>/dashboard/<dashboard>";
        private static final String HARDJSON = "{\"filter_id\":\"5dce58d33cdea20001b3212b\",\"name\":\"<name>\",\"share\":true,\"content_parameters\":{\"type\":\"statistics_panel\",\"gadget\":\"overall_statistics\",\"metadata_fields\":[\"name\",\"number\",\"start_time\"],\"itemsCount\":50,\"content_fields\":[\"statistics$executions$total\",\"statistics$executions$passed\",\"statistics$executions$failed\",\"statistics$executions$skipped\",\"statistics$defects$product_bug$PB001\",\"statistics$defects$automation_bug$AB001\",\"statistics$defects$system_issue$SI001\",\"statistics$defects$no_defect$ND001\",\"statistics$defects$to_investigate$TI001\"],\"widgetOptions\":{\"viewMode\":[\"panel\"],\"filterName\":[\"DEMO_FILTER#_demo\"]}},\"description\":\"<descr>\"}";
        private static final String HARDUpJSON = "{\"name\":\"<name>\",\"share\":true,\"content_parameters\":{\"type\":\"statistics_panel\",\"gadget\":\"overall_statistics\",\"metadata_fields\":[\"name\",\"number\",\"start_time\"],\"itemsCount\":50,\"content_fields\":[\"statistics$executions$total\",\"statistics$executions$passed\",\"statistics$executions$failed\",\"statistics$executions$skipped\",\"statistics$defects$product_bug$PB001\",\"statistics$defects$automation_bug$AB001\",\"statistics$defects$system_issue$SI001\",\"statistics$defects$no_defect$ND001\",\"statistics$defects$to_investigate$TI001\"],\"widgetOptions\":{\"viewMode\":[\"panel\"],\"filterName\":[\"DEMO_FILTER#_demo\"]}},\"filter_id\":\"5dce58d33cdea20001b3212b\",\"description\":\"<descr>\"}";

        public String delete(String projectName, String dashboardId, String widgetId) {
            String payload = "{\"deleteWidget\":\"" + widgetId + "\"}";
            return client.putWithToken(ADD_WIDGET_TO_DASHBOARD.replace("<project>", projectName).replace("<dashboard>", dashboardId), payload, authToken);
        }

        public String updateName(String project, String widgetId, String newName) {
            String jsonFromHardcoded = HARDUpJSON.replace("<name>", newName).replace("<descr>", newName + "_description");
            return client.putWithToken(WIDGET.replace("<project>", project) + "/" + widgetId, jsonFromHardcoded, authToken);
        }

        public String createSimpleWidget(String project, String name) {
            String jsonFromHardcoded = HARDJSON.replace("<name>", name).replace("<descr>", name + "_description");
            return client.postWithToken(WIDGET.replace("<project>", project), jsonFromHardcoded, authToken);
        }

        public String getAll(String projectName) {
            return client.getWithToken(ALL_WIDGETS.replace("<project>", projectName), authToken);
        }

        public String addToDashboard(String projectName, String dashboardId, String widgetId) {
            String payload = "{\"addWidget\":{\"widgetId\":\"" + widgetId + "\",\"widgetPosition\":[0,0],\"widgetSize\":[12,7]}}";
            return client.putWithToken(ADD_WIDGET_TO_DASHBOARD.replace("<project>", projectName).replace("<dashboard>", dashboardId), payload, authToken);
        }
    }

    public class Dashboard {

        private final String DASHBOARD = "/<project>/dashboard";

        public String update(String projectName, String dashboardId, HashMap<String, String> values) {
            JSONObject obj = new JSONObject();
            for (String key : values.keySet()) {
                obj.put(key, values.get(key));
            }
            return client.putWithToken(DASHBOARD.replace("<project>", projectName) + "/" + dashboardId, obj.toString(), authToken);
        }

        public String get(String projectName, String dashboardId) {
            return client.getWithToken(DASHBOARD.replace("<project>", projectName) + "/" + dashboardId, authToken);
        }

        public String delete(String projectName, String dashboardId) {
            return client.deleteWithToken(DASHBOARD.replace("<project>", projectName) + "/" + dashboardId, authToken);
        }

        public String getAll(String projectName) {
            return client.getWithToken(DASHBOARD.replace("<project>", projectName), authToken);
        }

        public String create(String projectName, String dashboardName) {
            JSONObject obj = new JSONObject();
            obj.put("name", dashboardName)
                    .put("description", dashboardName + "_description")
                    .put("share", true);
            return client.postWithToken(DASHBOARD.replace("<project>", projectName), obj.toString(), authToken);
        }

    }

    // Service methods

    private void init() {
        this.client = Config.getApiClient().equals("apachehttpclient") ? new HttpClient() : new RAssured();
        this.authToken = getAuthToken();
    }

    private String getAuthToken() {
        String resp = client.postWithoutBaseUrl(Config.getUrl() + "uat/sso/oauth/token?grant_type=password&password=" + password + "&username=" + username, "");
        return new JSONObject(resp).getString("access_token");
    }

    public void getCookies() {
        DriverManager.set(new Driver());
        List<Cookie> cookies = client.getCookies(Config.getUrl() + "uat/sso/me", authToken);
        DriverManager.get().goTo("");
//        DriverManager.getDriver().manage().deleteAllCookies();
        for (Cookie cookie : cookies) {
            DriverManager.getDriver().manage().addCookie(cookie);
        }
        DriverManager.get().goTo("ui/#superadmin_personal");
        String z = "a";
    }


}
