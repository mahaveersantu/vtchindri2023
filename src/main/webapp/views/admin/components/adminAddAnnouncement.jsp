
<div tabindex="-1" id="addAnnModal" class="modal hide fade in">
	<div class="modal-dialog">
		<div class="modal-content modal-body ">
			<div class="modal-header ">
				<div class="modal-title d-flex justify-content-center">

					<h5>Add Announcement</h5>
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
									<form action="${pageContext.request.contextPath}/saveAndUpdateArthikSahyog" method="post">

										<div class="col-md-12">
											<input class="form-control" type="text" name="name"
												placeholder="Full Name" required>

										</div>

										<div class="col-md-12">
											<input class="form-control" type="text" name="address"
												placeholder="Address" required>

										</div>
										<div class="col-md-12">
											<input class="form-control" type="text" name="mobile"
												placeholder="Mobile No" required>

										</div>
										<div class="col-md-12">
											<input class="form-control" type="text" name="announceAmount"
												placeholder="announceAmount" required>

										</div>
										<div class="col-md-12  mt-3">
											 <input class="form-control" type="file" placeholder="Photo"
												id="formFile">
										</div>
									
										<div class="form-button mt-3">
											<button  type="submit" class="btn btn-primary">Save
												Announcement</button>
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
	<button data-bs-toggle="modal" data-bs-target="#addAnnModal">Add
		Announcement</button>


</div>
<script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addAnnModal').modal({
			backdrop : 'static',
			keyboard : false
		})
	});
</script>