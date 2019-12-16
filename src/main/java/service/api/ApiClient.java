package service.api;

import Enums.Users;
import Interfaces.RestClient;
import org.json.JSONObject;
import service.Config;
import service.UserFactory;

import java.util.HashMap;
import java.util.Map;

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
        private static final String USER_EP = "/user/<id>";

        public String update(String userId, HashMap<String, String> values) {
            JSONObject obj = new JSONObject();
            for (String key : values.keySet()) {
                obj.put(key, values.get(key));
            }
            return client.putWithToken(USER_EP.replace("<id>", userId), obj.toString(), authToken);
        }

        public String logout() {
            return client.deleteWithoutBaseUrl(Config.getUrl() + "uat/sso/me", authToken);
        }

        public String delete(String userId) {
            return client.deleteWithToken(USER_EP.replace("<id>", userId), authToken);
        }

        public String getAll() {
            return client.getWithToken(GET_ALL_USERS, authToken);
        }

        public String getById(String userId) {
            return client.getWithToken(USER_EP.replace("<id>", userId), authToken);
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

        private static final String ALL_WIDGETS = "/%s/widget/names/all";
        private static final String WIDGET_EP = "/%s/widget";
        private static final String ADD_WIDGET_TO_DASHBOARD = "/%s/dashboard/%s";
        private static final String HARD_JSON = "{\"filter_id\":\"5dce58d33cdea20001b3212b\",\"name\":\"%s\",\"share\":true,\"content_parameters\":{\"type\":\"statistics_panel\",\"gadget\":\"overall_statistics\",\"metadata_fields\":[\"name\",\"number\",\"start_time\"],\"itemsCount\":50,\"content_fields\":[\"statistics$executions$total\",\"statistics$executions$passed\",\"statistics$executions$failed\",\"statistics$executions$skipped\",\"statistics$defects$product_bug$PB001\",\"statistics$defects$automation_bug$AB001\",\"statistics$defects$system_issue$SI001\",\"statistics$defects$no_defect$ND001\",\"statistics$defects$to_investigate$TI001\"],\"widgetOptions\":{\"viewMode\":[\"panel\"],\"filterName\":[\"DEMO_FILTER#_demo\"]}},\"description\":\"%s\"}";
        private static final String HARD_UP_SON = "{\"name\":\"%s\",\"share\":true,\"content_parameters\":{\"type\":\"statistics_panel\",\"gadget\":\"overall_statistics\",\"metadata_fields\":[\"name\",\"number\",\"start_time\"],\"itemsCount\":50,\"content_fields\":[\"statistics$executions$total\",\"statistics$executions$passed\",\"statistics$executions$failed\",\"statistics$executions$skipped\",\"statistics$defects$product_bug$PB001\",\"statistics$defects$automation_bug$AB001\",\"statistics$defects$system_issue$SI001\",\"statistics$defects$no_defect$ND001\",\"statistics$defects$to_investigate$TI001\"],\"widgetOptions\":{\"viewMode\":[\"panel\"],\"filterName\":[\"DEMO_FILTER#_demo\"]}},\"filter_id\":\"5dce58d33cdea20001b3212b\",\"description\":\"%s\"}";

        public String delete(String projectName, String dashboardId, String widgetId) {
            String payload = "{\"deleteWidget\":\"" + widgetId + "\"}";
            return client.putWithToken(String.format(ADD_WIDGET_TO_DASHBOARD, projectName, dashboardId), payload, authToken);
        }

        public String updateName(String project, String widgetId, String newName) {
            String jsonFromHardcoded = String.format(HARD_UP_SON, newName, newName + "_description");
            return client.putWithToken(String.format(WIDGET_EP, project) + "/" + widgetId, jsonFromHardcoded, authToken);
        }

        public String createSimpleWidget(String project, String name) {
            String jsonFromHardcoded = String.format(HARD_JSON, name, name + "_description");
            return client.postWithToken(String.format(WIDGET_EP, project), jsonFromHardcoded, authToken);
        }

        public String getAll(String projectName) {
            return client.getWithToken(String.format(ALL_WIDGETS, projectName), authToken);
        }

        public String addToDashboard(String projectName, String dashboardId, String widgetId) {
            String payload = "{\"addWidget\":{\"widgetId\":\"" + widgetId + "\",\"widgetPosition\":[0,0],\"widgetSize\":[12,7]}}";
            return client.putWithToken(String.format(ADD_WIDGET_TO_DASHBOARD, projectName, dashboardId), payload, authToken);
        }
    }

    public class Dashboard {

        private static final String DASH_BOARD_EP = "/%s/dashboard";

        public String update(String projectName, String dashboardId, Map<String, String> values) {
            JSONObject obj = new JSONObject();
            for (String key : values.keySet()) {
                obj.put(key, values.get(key));
            }
            return client.putWithToken(String.format(DASH_BOARD_EP, projectName) + "/" + dashboardId, obj.toString(), authToken);
        }

        public String get(String projectName, String dashboardId) {
            return client.getWithToken(String.format(DASH_BOARD_EP, projectName) + "/" + dashboardId, authToken);
        }

        public String delete(String projectName, String dashboardId) {
            return client.deleteWithToken(String.format(DASH_BOARD_EP, projectName) + "/" + dashboardId, authToken);
        }

        public String getAll(String projectName) {
            return client.getWithToken(String.format(DASH_BOARD_EP, projectName), authToken);
        }

        public String create(String projectName, String dashboardName) {
            JSONObject obj = new JSONObject();
            obj.put("name", dashboardName)
                    .put("description", dashboardName + "_description")
                    .put("share", true);
            return client.postWithToken(String.format(DASH_BOARD_EP, projectName), obj.toString(), authToken);
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

//    public void getCookies() {
//        DriverManager.set(new Driver());
//        List<Cookie> cookies = client.getCookies(Config.getUrl() + "uat/sso/me", authToken);
//        DriverManager.get().goTo("");
//        for (Cookie cookie : cookies) {
//            DriverManager.getDriver().manage().addCookie(cookie);
//        }
//        DriverManager.get().goTo("ui/#superadmin_personal");
//        String z = "a";
//    }


}
