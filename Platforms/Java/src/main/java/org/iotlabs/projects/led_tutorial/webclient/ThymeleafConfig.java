package org.iotlabs.projects.led_tutorial.webclient;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import spark.ModelAndView;

import java.util.Map;

public class ThymeleafConfig extends TemplateEngine{
  private static final String DEFAULT_PREFIX = "templates/";
  private static final String DEFAULT_SUFFIX = ".html";
  private static final String DEFAULT_TEMPLATE_MODE = "HTML5";
  private static final long DEFAULT_CACHE_TTL_MS = 3600000L;

  private TemplateEngine templateEngine;
  public ThymeleafConfig(){
    this(DEFAULT_PREFIX,DEFAULT_SUFFIX);
  }

  public ThymeleafConfig(String prefix, String suffix){
    ITemplateResolver defaultTemplateResolver = createDefaultTemplateResolver(prefix, suffix);
    initialize(defaultTemplateResolver);
  }
  public ThymeleafConfig(ITemplateResolver templateResolver) {
    initialize(templateResolver);
  }
  private void initialize(ITemplateResolver templateResolver) {
    templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
  }

  public String render(ModelAndView modelAndView) {
    Object model = modelAndView.getModel();

    if (model instanceof Map) {
      Context context = new Context();
      context.setVariables((Map<String, Object>) model);
      return templateEngine.process(modelAndView.getViewName(), context);
    } else {
      throw new IllegalArgumentException("modelAndView.getModel() must return a java.util.Map");
    }
  }

  private static ITemplateResolver createDefaultTemplateResolver(String prefix, String suffix) {
    final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setTemplateMode(DEFAULT_TEMPLATE_MODE);

    templateResolver.setPrefix(
        prefix != null ? prefix : DEFAULT_PREFIX
    );

    templateResolver.setSuffix(
        suffix != null ? suffix : DEFAULT_SUFFIX
    );

    templateResolver.setCacheTTLMs(DEFAULT_CACHE_TTL_MS);
    return templateResolver;
  }
}
