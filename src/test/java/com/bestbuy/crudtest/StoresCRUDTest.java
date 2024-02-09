package com.bestbuy.crudtest;

import com.bestbuy.steps.StoresSteps;
import com.bestbuy.testbase.TestBase;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.annotations.WithTag;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static com.bestbuy.utils.TestUtils.getRandomValue;
import static com.bestbuy.utils.TestUtils.getRandomValueInt;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;


@RunWith(SerenityRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class StoresCRUDTest extends TestBase {
    static String name = "Vijay Thalapathy" + getRandomValue();
    static String type = "TVK";
    static String address = "Theri";
    static String address2 = "Mersal Bigil";
    static String city = "Chennai";
    static String state = "Tamil Nadu";
    static String zip = "620002";
    static String lat = "3.27" + getRandomValueInt();
    static String lng = "6.87" + getRandomValueInt();
    static String hours = "Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8";
    static int storeId;

    @Steps
    StoresSteps storesSteps;

    @WithTag("StoreCrudTest")
    @Title("This test will create a new store")
    @Test
    public void test001() {

        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat,
                lng, hours);
        storeId = response.extract().path("id");
    }

    @WithTag("StoreCrudTest")
    @Title("Update the store name and verify the updated information")
    @Test
    public void test002() {
        name = name + "_updated";

        storesSteps.updateStore(storeId, name, null, null, null,
                null, null, null, null, null, null).statusCode(200);

        ValidatableResponse response = storesSteps.getStoreById(storeId);
        assertThat(response.extract().path("name"), equalTo(name));
    }

    @WithTag("StoreCrudTest")
    @Title("Delete the store and verify if the store  is deleted")
    @Test
    public void test003() {
        storesSteps.deleteStore(storeId).statusCode(200);
        storesSteps.getStoreById(storeId).statusCode(404);
    }

}
