package org.iotlabs.webserver.blueprints;

import org.iotlabs.models.general.User;
import org.iotlabs.util.StringUtils;
import org.iotlabs.webserver.httpexceptions.Http4xx;
import org.iotlabs.webserver.literals.ContentType;
import org.iotlabs.webserver.utils.Checker;
import org.iotlabs.webserver.utils.ResponseSetter;
import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;

import java.util.HashMap;
import java.util.Map;

import static org.iotlabs.webserver.blueprints.Complex.complex;

public class AuthBluePrint extends AbstractBluePrint {


    public AuthBluePrint() {
        super("auth");
    }

    @Override
    public void register() {
        complex(getEndPoint("login"))
                .get(
                        (req, res) -> {
                            Map<String, Object> scopes = new HashMap<>();
                            scopes.put("title", "IotLabs");
                            scopes.put("admin_mail", "toori67@gmail.com");
                            return new ModelAndView(scopes,"login.html");
                        }, new MustacheTemplateEngine(getTemplatePath())
                )
                .post((req, res) -> {
                    String username = req.queryParams("username");
                    String passwd = StringUtils.sha256(req.queryParams("password"));
                    User user = Checker.authenticate(username, passwd);
                    if (user == null) {
                        req.session().removeAttribute("auth");
                        ResponseSetter.setErrorResponse(res, new Http4xx(404, "not authorized"));
                        return res.body();
                    } else {
                        if (Checker.isRequestingHtml(req)) {
                            res.redirect("/");
                        }
                        req.session().attribute("auth", true);
                        res.type(ContentType.JSON);
                        res.body(user.toJsonString());
                        res.status(200);
                        return res.body();
                    }
                });
    }
}
