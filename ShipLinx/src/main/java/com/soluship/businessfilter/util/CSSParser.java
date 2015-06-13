package com.soluship.businessfilter.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cz.vutbr.web.css.CSSException;
import cz.vutbr.web.css.CSSFactory;
import cz.vutbr.web.css.Declaration;
import cz.vutbr.web.css.RuleBlock;
import cz.vutbr.web.css.RuleMedia;
import cz.vutbr.web.css.RuleSet;
import cz.vutbr.web.css.StyleSheet;
import cz.vutbr.web.css.Term;

 
/**
 *
 * @author selva
 */
public class CSSParser {
 
	/**
	 * @param args
	 */
	public   List<CSSClass> getAllclassofCSS(String file) {
		System.out.println(file);
		 try {

			@SuppressWarnings("deprecation")
			StyleSheet style = CSSFactory.parse(file);
			// obtain the number of rules
			System.out.println("#of rules parsed: " + style.size());
			// go through the rules
			List<CSSClass> cssList = new ArrayList<CSSClass>();
			   String property="";
	           String value="";
			for (RuleBlock<?> rule : style) {
				CSSClass css = new CSSClass();
				css.setPropertyMap(new HashMap<String, String>());
				if (rule instanceof RuleSet) {
					RuleSet set = (RuleSet) rule;

					System.out.println("Selectors: " + set.getSelectors());
					css.setSelector(set.getSelectors().toString());

					System.out.println("Declarations:");
					for (Declaration decl : set) {

						System.out.println(" Property: " + decl.getProperty());
						property=(decl.getProperty().toString());
						System.out.println(" Values: ");
						value="";
						for (Term<?> term : decl) {
							value+=term;
							System.out.println(" " + term);
						}
						css.getPropertyMap().put(property,value);

					}
					cssList.add(css);
				} else if (rule instanceof RuleMedia) {
					RuleMedia media = (RuleMedia) rule;
					System.out.println("Media: " + media.getMediaQueries());
					for (RuleSet set : media) {
						// process similarly to the RuleSet processing above
						System.out.println(" Rule: " + set);
					}
				} else {
					// other rules such as @page, @viewport etc.
					System.out.println("Other rule " + rule);
				}
			}

			System.out.println("--------------------------------------------");
			for (RuleBlock<?> rule : style) {
				if (rule instanceof RuleSet) {
					RuleSet set = (RuleSet) rule;
					System.out.println("Selectors: " + set.getSelectors());
					System.out.println("Declarations:");
					for (Declaration decl : set) {
						System.out.println(" Property: " + decl.getProperty());
						System.out.println(" Values: ");
						for (Term<?> term : decl)
							System.out.println(" " + term);
					}
				} else if (rule instanceof RuleMedia) {
					RuleMedia media = (RuleMedia) rule;
					System.out.println("Media: " + media.getMediaQueries());
					for (RuleSet set : media) {
						// process similarly to the RuleSet processing above
						System.out.println(" Rule: " + set);
					}
				} else {
					// other rules such as @page, @viewport etc.
					System.out.println("Other rule " + rule);
				}
				return cssList;
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (CSSException e) {
			e.printStackTrace();
		} 
		return null;
	}
 
 

	 
}
