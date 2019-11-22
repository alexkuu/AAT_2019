package apiTests;

import Enums.Users;
import org.json.JSONObject;
import org.testng.annotations.Test;
import service.api.ApiClient;

import java.util.HashMap;

import static service.assertions.Assertion.*;
import static utils.Utils.genRandomString;

public class SmokeApiTest {

    @Test
    public static void createModifyRemoveUser() {
        ApiClient api = new ApiClient(Users.ADMIN);
        api.getCookies();
        String userName = "test_user_" + genRandomString();

        // Create user
        String createResp = api.user.create(userName, "testtest", "test user" + genRandomString(), "USER", "CUSTOMER");
        assertEquals(new JSONObject(createResp).get("login"), userName);

        // Find him in users list
        assertTrue(new JSONObject(api.user.getAll()).get("content").toString().contains(userName));

        // Get user details and full name
        String oldFullName = new JSONObject(api.user.getById(userName)).getString("full_name");

        // Update full user name
        String newFullName = "new_full_name " + genRandomString();
        HashMap<String, String> params = new HashMap<>();
        params.put("full_name", newFullName);
        api.user.update(userName, params);

        String updatedFullName = new JSONObject(api.user.getById(userName)).getString("full_name");
        assertNotEquals(oldFullName, newFullName);
        assertEquals(newFullName, updatedFullName);

        // Delete user
        api.user.delete(userName);

        // Verify user not in list anymore
        assertFalse(new JSONObject(api.user.getAll()).get("content").toString().contains(userName));
    }

    @Test
    public static void createModifyRemoveDashboard() {
        ApiClient api = new ApiClient(Users.ADMIN);
        String dashboardName = "test_dash_" + genRandomString();
        String project = "superadmin_personal";

        // Create dashboard
        String createDashResp = api.dashboard.create(project, dashboardName);
        assertTrue(new JSONObject(createDashResp).getString("id") != null);
        String dashboardId = new JSONObject(createDashResp).getString("id");

        // Verify dashboard in list
        assertTrue(api.dashboard.getAll(project).contains(dashboardName));

        // Update description
        String newDescription = "updated_description_" + genRandomString();
        HashMap<String, String> params = new HashMap<>();
        params.put("description", newDescription);
        api.dashboard.update(project, dashboardId, params);

        // Get dashboardDetails
        String dashboardDetails = api.dashboard.get(project, dashboardId);
        String description = new JSONObject(dashboardDetails).getString("description");

        assertEquals(description, newDescription);

        // Delete dashboard
        api.dashboard.delete(project, dashboardId);

        // Verify dashboard not in list
        assertFalse(api.dashboard.getAll(project).contains(dashboardName));
    }

    @Test
    public static void createModifyRemoveWidget() {
        ApiClient api = new ApiClient(Users.ADMIN);
        String dashboardName = "test_dash_" + genRandomString();
        String project = "superadmin_personal";
        String widgetName = "test_widget_" + genRandomString();

        // Create dashboard
        String dashboardId = new JSONObject(api.dashboard.create(project, dashboardName)).getString("id");

        // Create widget
        String widgetId = new JSONObject(api.widget.createSimpleWidget(project, widgetName)).getString("id");
        assertTrue(api.widget.getAll(project).contains(widgetName));

        // Add widget to dashboard
        api.widget.addToDashboard(project, dashboardId, widgetId);

        // Update name and description
        String newName = "updated_test_widget_" + genRandomString();
        api.widget.updateName(project, widgetId, newName);

        assertFalse(api.widget.getAll(project).contains(widgetName));
        assertTrue(api.widget.getAll(project).contains(newName));

        // Delete widget
        api.widget.delete(project, dashboardId, widgetId);
        assertFalse(api.widget.getAll(project).contains(newName));

        // Delete dashboard
        api.dashboard.delete(project, dashboardId);
    }

}
