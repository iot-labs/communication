package org.iotlabs.projects.led_tutorial.webclient;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.eclipse.jetty.io.RuntimeIOException;
import spark.ModelAndView;
import spark.TemplateEngine;

import java.io.IOException;
import java.io.StringWriter;

public class MustacheTemplateEngine extends TemplateEngine{
  private MustacheFactory mustacheFactory;
  public MustacheTemplateEngine() {
    mustacheFactory = new DefaultMustacheFactory("templates");
  }
  public MustacheTemplateEngine(String resourceRoot) {
    mustacheFactory = new DefaultMustacheFactory(resourceRoot);
  }
  public MustacheTemplateEngine(MustacheFactory mustacheFactory) {
    this.mustacheFactory = mustacheFactory;
  }

  @Override
  public String render(ModelAndView modelAndView) {
    String viewName = modelAndView.getViewName();
    Mustache mustache = mustacheFactory.compile(viewName);
    StringWriter stringWriter = new StringWriter();
    try {
      mustache.execute(stringWriter, modelAndView.getModel()).close();
    } catch (IOException e) {
      throw new RuntimeIOException(e);
    }
    return stringWriter.toString();
  }
}
