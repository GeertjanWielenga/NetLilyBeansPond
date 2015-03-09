@TemplateRegistrations({
    //Origin: http://tex.blogoverflow.com/2011/11/sheet-music-with-lilypond-and-frescobaldi/
    @TemplateRegistration(
            requireProject = false,
            folder = "LilyPond",
            displayName = "Happy Birthday, Java!",
            content = "birthday.ly"),
    //Origin: http://en.wikipedia.org/wiki/LilyPond
    @TemplateRegistration(
            requireProject = false,
            folder = "LilyPond",
            displayName = "Excerpt from 'fibonacci' - Patrick McCarty",
            content = "fibonacci.ly"),
    @TemplateRegistration(
            requireProject = false,
            folder = "LilyPond",
            displayName = "Oktober in het Oosterpark - Mientje Deurloo",
            content = "oosterpark.ly")
})
package org.netbeans.nlbp;

import org.netbeans.api.templates.TemplateRegistration;
import org.netbeans.api.templates.TemplateRegistrations;
