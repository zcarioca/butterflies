<%@ page import="com.zcarioca.butterflies.Family"%>
<html>
<head>
<title>Families</title>
<meta name="layout" content="contentarea" />
</head>
<body>
   <h1>
      ${family.scientificName}
   </h1>
   <div id="family-area">
      <div id="common-name" class="detail-name">
         <span class="key">Common Name</span> <span class="value">${family.commonName }</span>
      </div>
      <div id="super-family" class="detail-name">
         <span class="key">Super Family</span>
         <span class="value">
            <g:if test="${family.superFamily}">
               <a href="#family?action=show&id=${family.superFamily.id}">${family.superFamily }</a>
            </g:if>
         </span>
      </div>
      <div id="sub-families" class="detail-list">
         <span class="key">Sub Families</span>
         <g:if test="${family.subFamilies }">
            <ul class="value">
               <g:each in="${family.subFamilies.sort()}" var="subFamily">
                  <li><a href="#family?action=show&id=${subFamily.id}">${subFamily}</a></li>
               </g:each>
            </ul>
         </g:if>
      </div>
      <div id="description" class="detail-description">
        <span class="key">Description</span>
        <span class="value"><xwiki:render>${family.description}</xwiki:render></span>
      </div>
      <div id="species" class="detail-list">
        <span class="key">Species in this Group</span>
        <g:if test="${species}">
          <ul class="value">
            <g:each in="${species}" var="spec">
               <li><a href="#lepidoptera?action=show&id=${spec.id}">${spec}</a></li>
            </g:each>
          </ul>
        </g:if>
      </div>
   </div>
<g:javascript>
  jQuery('#species > .key').sliderToggle('#species > ul');
</g:javascript>
</body>
</html>