/**
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package com.github.jacekszymanski.realcamel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import org.apache.camel.model.rest.RestBindingMode;

@Component
public class RestConfiguration extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
            .component("servlet")
            .bindingMode(RestBindingMode.auto)
                .dataFormatProperty("json.out.disableFeatures", "WRITE_DATES_AS_TIMESTAMPS")
            .clientRequestValidation(true);
    }
}
