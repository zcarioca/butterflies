<html>
<head>
<title>Lepidoptera</title>
<meta charset="UTF-8" />
<meta name="layout" content="contentarea" />
</head>
<body>
   <ul class="speciesList">
      <g:each in="${lepidoptera}" var="species">
         <li><a href="#lepidoptera?action=show&id=${species.id}">${species}</a></li>
      </g:each>
   </ul>
</body>
</html>