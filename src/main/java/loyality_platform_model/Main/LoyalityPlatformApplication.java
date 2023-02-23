package loyality_platform_model.Main;

import loyality_platform_model.Interface.UI_Cliente;
import loyality_platform_model.Interface.UI_Home;
import loyality_platform_model.Utils.InitProjectData;


public class LoyalityPlatformApplication {
    public static void main(String[] args) {

        InitProjectData.getInstance();
        UI_Home home = new UI_Home();
        home.welcomePage();

    }
}