<html>
<head>
<title>Lepidoptera</title>
<meta charset="UTF-8" />
<meta name="layout" content="contentarea" />
</head>
<body>
   <h1>Species</h1>
   <div id="species-search-area">
      <span class="key">Search</span>
      <span class="value">
         <g:textField name="term"/>
      </span>
   </div>
   <div id="species-search-results"></div>
   <div id="species-list-area">
      <ul class="speciesList">
         <g:each in="${lepidoptera}" var="species">
            <li><a href="#lepidoptera?action=show&id=${species.id}">${species}</a></li>
         </g:each>
      </ul>
   </div>
<g:javascript>
   jQuery('input#term').termSearch('#species-search-results', '#species-list-area', {action: 'simpleNameSearch'});
</g:javascript>
</body>
</html>