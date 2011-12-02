<%@ page import="com.zcarioca.butterflies.Family"%>
<html>
<head>
<title>Families</title>
<meta name="layout" content="contentarea" />
</head>
<body>
   <h1>Families</h1>
   <ul id="superFamilies" class="familyList">
      <g:each in="${families}" var="superFamily">
         <li>
            <a href="#family?action=show&id=${superFamily.id}">${superFamily}</a> <g:if test="${superFamily.subFamilies }">
               <ul id="families" class="familyList">
                  <g:each in="${superFamily.subFamilies.sort() }" var="family">
                     <li>
                        <a href="#family?action=show&id=${family.id}">${family}</a><g:if test="${family.subFamilies }">
                           <ul id="subFamilies" class="familyList">
                              <g:each in="${family.subFamilies.sort()}" var="subFamily">
                                 <li>
                                    <a href="#family?action=show&id=${subFamily.id}">${subFamily}</a>
                                 </li>
                              </g:each>
                           </ul>
                        </g:if>
                     </li>
                  </g:each>
               </ul>
            </g:if>
         </li>
      </g:each>
   </ul>
</body>
</html>