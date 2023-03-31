
<div tabindex="-1" id="addExpdModal" class="modal hide fade in">
	<div class="modal-dialog">
		<div class="modal-content modal-body ">
			<div class="modal-header ">
				<div class="modal-title d-flex justify-content-center">

					<h5 id="modaltitle"></h5>
				</div>

				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div
				class="modal-body d-flex justify-content-center bg-success text-white">



				<div class="form-body">
					<div class="row">
						<div class="form-holder">
							<div class="form-content">
								<div>

									<p>Fill in the data below.</p>
									<form
										action="${pageContext.request.contextPath}/adminSaveAndUpdateExpenditure"
										method="POST" enctype="multipart/form-data" name="editExpenditure">

										<div class="col-md-12">

											<input class="form-control" type="number"
												name="expdReceiptNo" placeholder="Bouchar No" required
												id="receiptE">

										</div>

										<div class="col-md-12">
											<!-- <input class="form-control" type="hidden" name="id" id="idE"> -->
											<input class="form-control mt-3" type="text"
												name="receiverName" placeholder="Receiver Full Name"
												required id="nameE" style="font-weight: bold;">

										</div>
										<div class="col-md-12">
											<!-- <input class="form-control" type="hidden" name="id" id="idE"> -->
											<input class="form-control mt-3" type="text"
												name="expdDetail" placeholder="Expenditure Details"
												required id="expdDetailE" style="font-weight: bold;">

										</div>

										<div class="col-md-12">
											<input class="form-control mt-3" type="number"
												name="expdAmount" placeholder="expditure Amount" required
												id="amountE">

										</div>
										<div class="col-md-12">
											<input class="form-control mt-3" type="date" name="Date"
												placeholder="expdDate" required id="expdDateE">

										</div>
										<div class="col-md-12 mt-2">
											<!-- <input class="form-control" type="text" name="sahyogDetail"
												placeholder="sahyog-Detail" required id="annouceE"> -->

											<label for="cars">Choose a Category:</label> <select
												class="form-select" name="catId">
												<c:forEach items="${allCategories}" var="allCat">
													<option value="${allCat.catId }" style="font-weight: bold;" id="catIdE">${allCat.catName }</option>
												</c:forEach>
											</select>
										</div>


										<div class="form-button mt-3">
											<button type="submit" class="btn btn-primary">Save
												Details</button>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>

		</div>
	</div>
</div>

<div class="text-center">
	<button class="btn btn-success" onclick="addExpenditure()">Add
		Expenditure Details</button>


</div>
<script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addGairModal').modal({
			backdrop : 'static',
			keyboard : false
		})

	});
	function addExpenditure() {
		$("#nameE").val("");
		$("#addressE").val("");
		$("#mobileE").val("");
		$("#detailsE").val("");
		$("#idE").val();
		$("#modaltitle").html("Add Expenditure");

		$('#addExpdModal').modal('show');
	}

	
</script>