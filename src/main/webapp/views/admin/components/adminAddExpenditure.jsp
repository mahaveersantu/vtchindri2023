
<div tabindex="-1" id="addGairModal" class="modal hide fade in">
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
										action="${pageContext.request.contextPath}/adminSaveAndUpdateGairAarthikSahyog"
										method="POST" enctype="multipart/form-data">

										<div class="col-md-12">
											<input class="form-control" type="hidden" name="id" id="idE">
											<input class="form-control" type="text" name="name"
												placeholder="Full Name" required id="nameE">

										</div>

										<div class="col-md-12">
											<input class="form-control" type="text" name="address"
												placeholder="Address" required id="addressE">

										</div>
										<div class="col-md-12">
											<input class="form-control" type="text" name="mobile"
												placeholder="Mobile No" required id="mobileE">

										</div>
										<div class="col-md-12 mt-3">
											<!-- <input class="form-control" type="text" name="sahyogDetail"
												placeholder="sahyog-Detail" required id="annouceE"> -->

											<textarea class="form-control" name="sahyogDetail" rows="4"
												cols="50" placeholder="Sahyog Details..." id="detailsE">

</textarea>
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
	<button onclick="addGairAarthik()">Add Expenditure Details</button>


</div>
<script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addGairModal').modal({
			backdrop : 'static',
			keyboard : false
		})

	});
	function addGairAarthik() {
		$("#nameE").val("");
		$("#addressE").val("");
		$("#mobileE").val("");
		$("#detailsE").val("");
		$("#idE").val();
		$("#modaltitle").html("Add Gair Aarthik");

		$('#addGairModal').modal('show');
	}
</script>