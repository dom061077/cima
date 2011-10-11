package com.medfire.chart;

import java.text.AttributedString;

import org.jfree.data.general.PieDataset;
import org.jfree.chart.labels.PieSectionLabelGenerator;

class CustomLabelGenerator implements PieSectionLabelGenerator {
	
   @Override
	public AttributedString generateAttributedSectionLabel(PieDataset arg0,
			Comparable arg1) {
		// TODO Auto-generated method stub
		return null;
	}

public String generateSectionLabel(final PieDataset dataset, final Comparable key) {
	   String temp = null;
	   if (dataset != null) {
		   temp = dataset.getValue(key).toString();
	   }
	   return temp;
   }
}