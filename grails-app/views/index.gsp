<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<script src="${resource(dir: 'js', file: 'tradeGame.js')}"></script>
		<script src="${resource(dir: 'js', file: 'rate.js')}"></script>
	</head>
	<body>
		<div id="banner">
			<div id="tradeGame" class="container">
				<div class="row">
					<div class="col-xs-2">
						
					</div>
					<div class="col-xs-3">
						<a href="#" class="thumbnail" id="myProduct">
							Produit courant
							<div class="clickWrapper"></div>
						</a>
					</div>
					<div class="col-xs-2 versus">
						<div id="exchangeCountDown">10s</div>
						<div class="glyphicon glyphicon-resize-horizontal"></div>
					</div>
					<div class="col-xs-3">
						<a href="#"  class="thumbnail" id="secondProduct">
							Produit porposé
							<div class="clickWrapper"></div>
						</a>
					</div>
					<div class="col-xs-2">
						<div id="gameCountDown"><span>1j10h29m</span><br>avant la fin du jeu</div>
						<div id="exchangeRemaining"><span>3</span> échanges restant dans ce jeu</div>
					</div>
				</div>
			</div>
		</div>
		<div id="container" class="container" >
			<g:link controller="index" action="logout" class="logout btn btn-default">Déconnexion</g:link>
			<div class="classementEloInfos">Ce tableau liste les produits qui vous correspondent le plus</div>
			<table class="table table-striped table-rank" >
				
			</table>
		</div>
	</body>
</html>
