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
      <div id="description" class="detail-description">
        <span class="key">Description</span>
        <span class="value"><xwiki:render>${species.description}</xwiki:render></span>
      </div>
   </div>
</body>
</html>