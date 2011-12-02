<%@ page import="com.zcarioca.butterflies.Family"%>
<html>
<head>
<title>Lepidotpera</title>
<meta charset="UTF-8"/>
<meta name="layout" content="contentarea" />
</head>
<body>
   <h1>
      ${species.scientificName}
   </h1>
   <div id="species-area">
      <div id="abbreviated-name" class="detail-name">
         <span class="key">Abbreviated Name</span> <span class="value">${species.abbreviatedName }</span>
      </div>
      <div id="common-name" class="detail-name">
         <span class="key">Common Name</span> <span class="value">${species.commonName }</span>
      </div>
      <div id="family" class="detail-name">
         <span class="key">Family</span>
         <span class="value">
            <a href="#family?action=show&id=${species.family.id}">${species.family}</a>
         </span>
      </div>
      <div id="states" class="detail-list">
         <span class="key">Found in States</span>
         <ul class="value">
            <g:each in="${species.states}" var="state">
               <li title="${state.name}"><a href="#lepidoptera?action=search&state=${state.abbreviation}">${state.abbreviation}</a></li>
            </g:each>
         </ul>
      </div>
      <div id="description" class="detail-description">
        <span class="key">Description</span>
        <span class="value"><xwiki:render>${species.description}</xwiki:render></span>
      </div>
      <div id="added" class="detail-name">
         <span class="key">Added On</span>
         <span class="value"><g:formatDate date="${species.createdDate }" format="MMM. d, yyyy h:mm a"></g:formatDate></span>
      </div>
      <div id="updated" class="detail-name">
         <span class="key">Last Updated On</span>
         <span class="value"><g:formatDate date="${species.lastModified }" format="MMM. d, yyyy h:mm a"></g:formatDate></span>
      </div>
   </div>
</body>
</html>