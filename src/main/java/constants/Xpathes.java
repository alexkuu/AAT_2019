package constants;

public class Xpathes {

    public static final String GRAY_SQUARE_PROPOSAL = "//div[@class='placeholder-content']";
    public static final String LOGOUT_LINK = "//ul[@id='userNavigator']//span[contains(.,'" + AppTexts.LOGOUT + "')]";
    public static final String SETTINGS_LINK = "//li[@id='settings']//i";
    public static final String ADD_NEW_DASHBOARD = "//span[text()='" + AppTexts.ADD_NEW_DASHBOARD + "']";
    public static final String NEW_DASHBOARD_NAME = "//input[@placeholder='" + AppTexts.ENTER_DASHBOARD_NAME + "']";
    public static final String NEW_DASHBOARD_SAVE_BUTTON = "//div[@class='modal-footer']//button[contains(.,'Add')]";
    public static final String DASHBOARD_CONFIRM_DELETE_BTN = "//button[contains(.,'" + AppTexts.DELETE + "')]";
    public static final String LOGIN_INPUT = "//input[@placeholder='" + AppTexts.LOGIN + "']";
    public static final String PASSWORD_INPUT = "//input[@placeholder='" + AppTexts.PASSWORD + "']";
    public static final String LOGIN_BUTTON = "//button[contains(.,'" + AppTexts.LOGIN + "')]";
    public static final String LOGIN_ERROR_MESSAGE = "//div[contains(.,'" + AppTexts.LOGIN_ERROR_MESSAGE + "')]";
    public static final String DASHBOARD_DELETED_MESSAGE = "//*[contains(.,'" + AppTexts.DASHBOARD_DELETED + "')]";
    public static final String DEMO_DASHBOARD_NAME_IN_BREADCRUMB = "//ul[@class='main-breadcrumbs']//span[text()='DEMO DASHBOARD#" + AppContstants.DEMO_DASHBOARD_PREFIX + "']";
    public static final String DEMO_DASHBOARD = "//div[@class='dashboard-content'][div[@class='name-wrapper']/p[text()='DEMO DASHBOARD#" + AppContstants.DEMO_DASHBOARD_PREFIX + "']]";
    public static final String SETTINGS_PAGE_TITLE = "//div[@id='headerBar']//span[text()='Settings']";
    public static final String SVG_TAG = "//*[local-name() = 'svg']";



    public static String DASHBOARD_DELETE_ICON(String name) {return "//p[text()='" + name + "']/following-sibling::div/i[text()='close']";}
    public static String DASHBOARD_BY_NAME(String name) {return "//div[@class='dashboard-container']//p[text()='" + name + "']";}
    public static String DASHBOARD_BY_NAME_FOR_OPEN(String name) {return "//div[@class='dashboard-content'][div[@class='name-wrapper']/p[text()='" + name + "']]";}
    public static String DASHBOARD_IN_BREADCRUMB(String name) {return "//ul[@class='main-breadcrumbs']//span[text()='" + name + "']";}

}
