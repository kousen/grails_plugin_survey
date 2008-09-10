<sel:test name="Plugin CRUD test">

 <sel:row line="open|${request.contextPath}/plugin"/>
 <sel:row line="verifyTextPresent|Home"/>

 <sel:row line="verifyTextPresent|Plugin List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|0"/>

 <sel:row line="clickAndWait|link=New Plugin"/>
 <sel:row line="verifyTextPresent|Create Plugin"/>
 <sel:row line="clickAndWait|//input[@value='Create']"/>
 <sel:row line="verifyTextPresent|Show Plugin"/>
 <sel:row line="clickAndWait|link=Plugin List"/>

 <sel:row line="verifyTextPresent|Plugin List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|1"/>

 <sel:row line="clickAndWait|link=1"/>
 <sel:row line="clickAndWait|//input[@value='Edit']"/>

 <sel:row line="verifyTextPresent|Edit Plugin"/>
 <sel:row line="clickAndWait|//input[@value='Update']"/>
 <sel:row line="verifyTextPresent|Show Plugin"/>
 <sel:row line="clickAndWait|link=Plugin List"/>

 <sel:row line="verifyTextPresent|Plugin List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|1"/>

 <sel:row line="clickAndWait|link=1"/>
 <sel:row line="clickAndWait|//input[@value='Delete']"/>
 <sel:row line="verifyConfirmation|Are you sure?"/>

 <sel:row line="verifyTextPresent|Plugin List"/>
 <sel:row line="verifyXpathCount|xpath=(//div[@class='list']//tbody/tr)|0"/>
</sel:test>
