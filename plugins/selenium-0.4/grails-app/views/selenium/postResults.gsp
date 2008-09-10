<html>
<head>
<link href="${createLinkTo(dir:'selenium/core',file:'selenium-test.css')}" type="text/css" rel="stylesheet">
 <style>
    #result { font-size:1.1em; width:100%;text-align: center; padding:10px;border:1px solid black;}
	.data {margin:20px;}
 </style>
</head>
<body>
    <h1>Selenium test results</h1>

    <div class="status_${params.result}" id="result">
        <strong>Test run ${params.result?.toUpperCase()}</strong>
    </div>

    <h2>Summary</h2>
<div class="data">
    <table id="summary">
    <tr><td>Total Time</td><td>${params.totalTime} seconds</td></tr>
    <tr class="status_passed"><td>Number of tests that passed</td><td>${params.numTestPasses}</td></tr>
    <tr class="status_passed"><td>Number of commands that passed</td><td>${params.numCommandPasses}</td></tr>
    <tr class="status_failed"><td>Number of tests that failed</td><td>${params.numTestFailures}</td></tr>
    <tr class="status_failed"><td>Number of commands that failed</td><td>${params.numCommandFailures}</td></tr>
    <tr class="status_failed"><td>Number of commands that errored</td><td>${params.numCommandErrors}</td></tr>
    </table>
</div>
    <h2>Suite</h2>
<div class="data">
    ${params.suite}
</div>

    <h2>Tests</h2>
<div class="data">
	<g:set var="i" value="0"/>
	<g:while test="${params.('testTable.'+(++i))}">

		${params.('testTable.'+i)}
<br/>
	</g:while>
</div>
</body>
</html>

