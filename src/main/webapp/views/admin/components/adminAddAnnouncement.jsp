
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
									<form class="requires-validation" novalidate>

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
										<!--       <div class="col-md-12">
                                <select class="form-select mt-3" required>
                                      <option selected disabled value="">Position</option>
                                      <option value="jweb">Junior Web Developer</option>
                                      <option value="sweb">Senior Web Developer</option>
                                      <option value="pmanager">Project Manager</option>
                               </select>
                                <div class="valid-feedback">You selected a position!</div>
                                <div class="invalid-feedback">Please select a position!</div>
                           </div>


                           <div class="col-md-12">
                              <input class="form-control" type="password" name="password" placeholder="Password" required>
                               <div class="valid-feedback">Password field is valid!</div>
                               <div class="invalid-feedback">Password field cannot be blank!</div>
                           </div>


                           <div class="col-md-12 mt-3">
                            <label class="mb-3 mr-1" for="gender">Gender: </label>

                            <input type="radio" class="btn-check" name="gender" id="male" autocomplete="off" required>
                            <label class="btn btn-sm btn-outline-secondary" for="male">Male</label>

                            <input type="radio" class="btn-check" name="gender" id="female" autocomplete="off" required>
                            <label class="btn btn-sm btn-outline-secondary" for="female">Female</label>

                            <input type="radio" class="btn-check" name="gender" id="secret" autocomplete="off" required>
                            <label class="btn btn-sm btn-outline-secondary" for="secret">Secret</label>
                               <div class="valid-feedback mv-up">You selected a gender!</div>
                                <div class="invalid-feedback mv-up">Please select a gender!</div>
                            </div> -->

										<!--  <div class="form-check">
                          <input class="form-check-input" type="checkbox" value="" id="invalidCheck" required>
                          <label class="form-check-label">I confirm that all data are correct</label>
                         <div class="invalid-feedback">Please confirm that the entered data are all correct!</div>
                        </div> -->


										<div class="form-button mt-3">
											<button id="submit" type="submit" class="btn btn-primary">Save
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