# Introduction

A plugin for the Oxygen XML editor to help encode English monarch regnal dates (Henry III to Henry VII) 
into a TEI/XML &lt;date/&gt;. The plugin was developed as part of the Medieval Gold Seam project of 
[Beyond 2022](https://beyond2022.ie/).

For example, "quindene of Easter 3 Edward III" will be rendered:

```
<date type="regnal" when="1329-05-07">quindene of Easter <supplied>1329</supplied> 3 Edward III</date>"
```

The regnal year might not be part of where the feast is given in the original text, but it is needed for
calculating the date, so square brackets can be used.

For example, 'quindene of Easter [3 Edward III]' will be rendered:

```
<date type="regnal" when="1329-05-07">quindene of Easter <supplied>1329</supplied> <supplied>3 Edward III</supplied></date>"
```

## Building

Most of the heavy lifting is done by code in this project:
https://github.com/ilrt/RegnalDate

Checkout the project and run `mvn install`.

In this project, run `mvn package`.

In the `target` folder there will be a packed called `RegnalDatePlugin.zip`.

## Installing in Oxygen

Unzip the file from fluff. You would see a `.xml` and a `.jar` file

In Oxygen, select:

`Help -> Install new add-ons...`

In the window that appears, select the folder icon to the right of 'Show add-ons from:'. Select extensions.xml from the
zip. This will update the table to show the plugin. Click the selection box and click 'Next'. Then click 'Install'. 

Note: It will give a warning that the software hasn't got a valid signature. Click 'Continue Anyway'.

Restart Oxygen.

## Using the plugin

Highlight the feast and select CTR+SHIFT+R