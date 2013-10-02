require 'calabash-android/calabash_steps'



When /^I enter "([^"]*)" and "([^"]*)" as operands$/ do |firstOperand, secondOperand|
  sleep 1
  performAction('enter_text_into_id_field', firstOperand,'first_operand')
  performAction('enter_text_into_id_field', secondOperand,'second_operand')
end

And /^I select "([^"]*)" operation$/ do |operation|
  sleep 1
if operation == "addition"
  performAction('click_on_view_by_id', 'radio_add')
  else
  performAction('click_on_view_by_id', 'radio_factorial')
end
end


When /^I press result button$/ do
  sleep 1

  performAction('click_on_view_by_id', 'btn_result')
end



When /^I enter numbers? "([^"]*)"( and "([^"]*)")?$/ do  |firstOperand,is_second, secondOperand|
  sleep 1

step %{I enter text "#{firstOperand}" into field with id "#{sections["first_operand"]}"}
if is_second
step %{I enter text "#{secondOperand}" into field with id "#{sections["second_operand"]}"}
end
end



Then(/^I should see the "(.*?)" in the result box$/) do |result|
  sleep 3
  performAction( 'assert_view_property', 'view_result', "text", result )
end