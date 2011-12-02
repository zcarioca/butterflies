<html>
<head>
<title>Lepidoptera</title>
<meta charset="UTF-8" />
<meta name="layout" content="contentarea" />
</head>
<body>
   <g:if test="${terms}">
      <h1>Search Results</h1>
      <div id="search-criteria">Criteria</div>
      <g:each in="${terms}" var="entry">
         <div class="detail-name">
            <span class="key">${entry.key}</span>
            <span class="value">${entry.value}</span>
         </div> 
      </g:each>
      <div id="search-results">Results</div>
   </g:if>
   <ul class="speciesList">
      <g:each in="${lepidoptera}" var="species">
         <li><a href="#lepidoptera?action=show&id=${species.id}">${species}</a></li>
      </g:each>
   </ul>
</body>
</html>