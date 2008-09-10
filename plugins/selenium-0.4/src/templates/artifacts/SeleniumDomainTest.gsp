<sel:test name="XclassNameX CRUD test">

 <sel:row line="open|${request.contextPath}/XpropertyNameX"/>
 <sel:row line="verifyTextPresent|Home"/>

 <sel:row line="verifyTextPresent|XclassNameX List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|0"/>

 <sel:row line="clickAndWait|link=New XclassNameX"/>
 <sel:row line="verifyTextPresent|Create XclassNameX"/>
 <sel:row line="clickAndWait|//input[@value='Create']"/>
 <sel:row line="verifyTextPresent|Show XclassNameX"/>
 <sel:row line="clickAndWait|link=XclassNameX List"/>

 <sel:row line="verifyTextPresent|XclassNameX List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|1"/>

 <sel:row line="clickAndWait|link=1"/>
 <sel:row line="clickAndWait|//input[@value='Edit']"/>

 <sel:row line="verifyTextPresent|Edit XclassNameX"/>
 <sel:row line="clickAndWait|//input[@value='Update']"/>
 <sel:row line="verifyTextPresent|Show XclassNameX"/>
 <sel:row line="clickAndWait|link=XclassNameX List"/>

 <sel:row line="verifyTextPresent|XclassNameX List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|1"/>

 <sel:row line="clickAndWait|link=1"/>
 <sel:row line="clickAndWait|//input[@value='Delete']"/>
 <sel:row line="verifyConfirmation|Are you sure?"/>

 <sel:row line="verifyTextPresent|XclassNameX List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|0"/>
</sel:test>
