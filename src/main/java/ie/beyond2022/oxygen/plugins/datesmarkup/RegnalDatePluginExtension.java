package ie.beyond2022.oxygen.plugins.datesmarkup;

import ie.beyond2022.utils.regnaldate.RegnalDate;
import ro.sync.exml.plugin.selection.SelectionPluginContext;
import ro.sync.exml.plugin.selection.SelectionPluginExtension;
import ro.sync.exml.plugin.selection.SelectionPluginResult;
import ro.sync.exml.plugin.selection.SelectionPluginResultImpl;

import java.io.IOException;

public class RegnalDatePluginExtension implements SelectionPluginExtension {

    public SelectionPluginResult process(SelectionPluginContext context) {

        RegnalDate regnalDate = null;
        String val;
        boolean suppliedRegnalYear = false;

        try {

            String textSelection = context.getSelection();
            textSelection = textSelection.replaceAll("\\s{2,}", " ");
            textSelection = textSelection.trim();
            if (textSelection.contains("[") && textSelection.contains("]")) {
                textSelection = textSelection.replaceAll("\\[", "").replaceAll("\\]", "");
                suppliedRegnalYear = true;
            }
            regnalDate = RegnalDate.parseString(textSelection);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (regnalDate != null) {

            String regnalText = regnalDate.getRegnalYearMonarch();

            if (suppliedRegnalYear) {
                regnalText = "<supplied>" + regnalText + "</supplied>";
            }

            if (regnalDate.isRange()) {
                String[] tmp = regnalDate.toString().split(":");
                val = "<date type=\"regnal\" from=\"" + tmp[0] + "\" to=\"" + tmp[1] + "\">"
                        + regnalText + " <supplied>" + regnalDate.getYear()
                        + "</supplied></date>";
                if (suppliedRegnalYear) {
                    val = "<supplied>" + val + "</supplied";
                }
            } else {

                val = "<date type=\"regnal\" when=\"" + regnalDate.toString() + "\">"
                        + regnalDate.getDayMonthFeastText() + " <supplied>" + regnalDate.getYear() + "</supplied> "
                        + regnalText + "</date>";
            }
        } else {
            val = "<date type='regnal' when=''>" + context.getSelection() + "</date>";
        }

        return new SelectionPluginResultImpl(val);

    }

}
