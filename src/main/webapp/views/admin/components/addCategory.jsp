
<div tabindex="-1" id="addCatModal" class="modal hide fade in">
	<div class="modal-dialog">
		<div class="modal-content modal-body ">
			<div class="modal-header ">
				<div class="modal-title d-flex justify-content-center">

					<h5>Add Category</h5>
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
									<form action="${pageContext.request.contextPath}/adminSaveAndUpdateCategory"  method="post" enctype="multipart/form-data">

										<div class="col-md-12">
										<input class="form-control" type="hidden" name="catId" id="idE">
											<input class="form-control" type="text" name="catName"
												placeholder="Category Name" required id="catNameE">

										</div>

										
										<div class="form-button mt-3">
											<button  type="submit" class="btn btn-primary">Save
												Category</button>
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
	<button onclick="addCategory()" class="btn btn-primary">Add
		Category</button>




</div>
<script src="assets/vendor/jquery-3.6.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#addCatModal').modal({
			backdrop : 'static',
			keyboard : false
		})
	});
	function addCategory() {
		$("#idE").val();
		$("#catNameE").val("");
		$("#modaltitle").html("Add Category");
		$('#addCatModal').modal('show');
	}
</script>